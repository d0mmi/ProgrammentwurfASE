import React from 'react';
import '../../App.css';
import { UserApi, User } from '../../api/UserApi';
import { Button, CircularProgress, Grid, Paper, TextField, Theme, Typography, withStyles } from '@material-ui/core';
import { withCookies, Cookies } from 'react-cookie';
import { Error } from '../../api/APIManager';

interface IState {
    user: User;
    name: string;
    email: string;
    loading: boolean;
}

const styles = (theme: Theme) => ({
    root: {
    },
    card_grid: {
        height: "100%",
        width: "100%"
    },
    paper: {
        marginTop: '25px',
        marginBottom: '25px',
        padding: '25px',
        height: "100%",
        width: "100%"
    },
    input: {
        marginTop: "15px",
        marginRight: "5px"
    },
});


class LoginPage extends React.Component<any, IState>
{
    classes: any;
    cookies: Cookies;

    constructor(props: any) {
        super(props);
        this.classes = this.props.classes;
        this.cookies = this.props.cookies
        this.fetchUser = this.fetchUser.bind(this);
        this.onSave = this.onSave.bind(this);
        this.onLogout = this.onLogout.bind(this);

        this.state = { user: this.cookies.get('user'), name: "", email: "", loading: false };
    }
    render() {

        if (this.state.user === null || this.state.user === undefined) {
            if (!this.state.loading) {
                this.setState({ loading: true });
                this.fetchUser();
            }
            return (<CircularProgress />);
        }

        return (
            <Grid item xs={12} className={this.classes.card_grid} >
                <Grid container direction="row" justify="space-evenly" alignItems="center" spacing={0} >
                    <Grid key={1} item xs={"auto"}>
                        <Paper className={this.classes.paper}>
                            <Typography variant="h4">Welcome {this.state.user.name} | {this.state.user.email}</Typography>
                            <Typography variant="h5">Edit Profile:</Typography>
                            <TextField required className={this.classes.input} id="outlined-basic" label="Name" variant="outlined" onInput={(e: React.ChangeEvent<HTMLInputElement>) => this.setState({ name: e.target.value })} />
                            <TextField required className={this.classes.input} id="outlined-basic" label="Email" variant="outlined" onInput={(e: React.ChangeEvent<HTMLInputElement>) => this.setState({ email: e.target.value })} />
                            <br />
                            <Button className={this.classes.input} variant="contained" color="primary" onClick={this.onSave}>Save</Button>
                            <Button className={this.classes.input} variant="contained" color="primary" onClick={this.onLogout}>Logout</Button>
                        </Paper>
                    </Grid>
                </Grid>
            </Grid>
        );

    }

    async fetchUser() {
        var response = await UserApi.get();

        if ((response as Error).message === undefined) {
            var user = response as User;
            this.cookies.set("user", user);
            this.setState({
                user: user
            });
        }
    }

    async onSave() {
        var name = (this.state.name.length === 0) ? null : this.state.name;
        var email = (this.state.email.length === 0) ? null : this.state.email;
        await UserApi.updateById(this.state.user.id, name, email);
        await this.fetchUser();
    }
    async onLogout() {
        this.cookies.remove('user');
        this.cookies.remove('session');
        window.location.reload();
    }


}

export default withStyles(styles, { withTheme: true })(withCookies(LoginPage));