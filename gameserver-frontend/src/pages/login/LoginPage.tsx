import React from 'react';
import '../../App.css';
import { UserApi, LoginResponse } from '../../api/UserApi';
import { Button, Grid, Paper, Snackbar, TextField, Theme, withStyles } from '@material-ui/core';
import { Alert } from '@material-ui/lab';
import { withCookies, Cookies } from 'react-cookie';

interface IState {
    name: string;
    email: string;
    password: string;
    login: boolean;
    sucessOpen: boolean;
    errorOpen: boolean;
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
        paddingTop: '25px',
        marginBottom: '25px',
        height: "100%",
        width: "300px"
    },
    input: {
        marginBottom: "15px"
    },
});


class LoginPage extends React.Component<any, IState>
{
    classes: any;
    cookies: Cookies;

    constructor(props: any) {
        super(props);
        console.log(props);
        this.classes = this.props.classes;
        this.cookies = this.props.cookies
        this.state = { name: "", email: "", password: "", login: true, sucessOpen: false, errorOpen: false };
        this.onLogin = this.onLogin.bind(this);
        this.onRegister = this.onRegister.bind(this);
        this.handleClose = this.handleClose.bind(this);
    }
    render() {
        return (
            <Grid item xs={12} className={this.classes.card_grid} >
                <Grid container direction="row" justify="space-evenly" alignItems="center" spacing={0} >
                    <Grid key={1} item xs={"auto"}>
                        <Paper className={this.classes.paper}>
                            {this.state.login ?
                                (<Button className={this.classes.input} variant="contained" color="primary" onClick={(e) => this.setState({ login: false })}>Register new User</Button>) :
                                (<div>
                                    <Button className={this.classes.input} variant="contained" color="primary" onClick={(e) => this.setState({ login: true })}>Login with User</Button>
                                    <TextField required className={this.classes.input} id="outlined-basic" label="Name" variant="outlined" onInput={(e: React.ChangeEvent<HTMLInputElement>) => this.setState({ name: e.target.value })} />
                                </div>)
                            }

                            <TextField required className={this.classes.input} id="outlined-basic" label="Email" variant="outlined" onInput={(e: React.ChangeEvent<HTMLInputElement>) => this.setState({ email: e.target.value })} />
                            <TextField required className={this.classes.input} id="outlined-basic" label="Password" type="password" variant="outlined" onInput={(e: React.ChangeEvent<HTMLInputElement>) => this.setState({ password: e.target.value })} />
                            {this.state.login ?
                                (<Button className={this.classes.input} variant="contained" color="primary" onClick={this.onLogin}>Login</Button>) :
                                (<Button className={this.classes.input} variant="contained" color="primary" onClick={this.onRegister}>Register</Button>)
                            }
                        </Paper>

                    </Grid>
                </Grid>
                <Snackbar open={this.state.sucessOpen} autoHideDuration={6000} onClose={this.handleClose}>
                    <Alert onClose={this.handleClose} variant="filled" severity="success"> This is a success message!</Alert>
                </Snackbar>
                <Snackbar open={this.state.errorOpen} autoHideDuration={6000} onClose={this.handleClose}>
                    <Alert onClose={this.handleClose} variant="filled" severity="error">This is an error message!</Alert>
                </Snackbar>
            </Grid>
        );

    }

    handleClose() {
        this.setState({ sucessOpen: false, errorOpen: false });
    }

    async onLogin() {
        var response = await UserApi.login(this.state.email, this.state.password);
        if ((response as LoginResponse).token !== undefined) {
            this.setState({ sucessOpen: true });
            this.cookies.set("session", (response as LoginResponse).token);
            window.location.href = window.location.protocol + "//" + window.location.host + "/";
        } else {
            this.setState({ errorOpen: true });
        }
    }

    async onRegister() {
        UserApi.register(this.state.name, this.state.email, this.state.password);
    }
}

export default withStyles(styles, { withTheme: true })(withCookies(LoginPage));