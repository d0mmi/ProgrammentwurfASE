import React from 'react';
import '../../App.css';
import { UserApi } from '../../api/UserApi';
import { Button, Grid, Paper, TextField, Theme, withStyles } from '@material-ui/core';

interface IState {
    name: string;
    email: string;
    password: string;
    login: boolean;
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

    constructor(props: any) {
        super(props);
        this.classes = this.props.classes;
        this.state = { name: "", email: "", password: "", login: true };
        this.onLogin = this.onLogin.bind(this);
        this.onRegister = this.onRegister.bind(this);
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
                            <TextField required className={this.classes.input} id="outlined-basic" label="Password" variant="outlined" onInput={(e: React.ChangeEvent<HTMLInputElement>) => this.setState({ password: e.target.value })} />
                            {this.state.login ?
                                (<Button className={this.classes.input} variant="contained" color="primary" onClick={this.onLogin}>Login</Button>) :
                                (<Button className={this.classes.input} variant="contained" color="primary" onClick={this.onRegister}>Register</Button>)
                            }
                        </Paper>

                    </Grid>
                </Grid>
            </Grid>
        );

    }


    onLogin() {
        //UserApi.login(this.state.email, this.state.password);
        console.log(this.state.email);
        console.log(this.state.password);
    }

    onRegister() {
        //UserApi.register(this.state.name, this.state.email, this.state.password);
        console.log(this.state.name);
        console.log(this.state.email);
        console.log(this.state.password);
    }
}

export default withStyles(styles, { withTheme: true })(LoginPage);