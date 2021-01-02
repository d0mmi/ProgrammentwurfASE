import React, { ChangeEvent } from 'react';
import {
    Button
} from "react-bootstrap";
import '../../App.css';
import { RouteComponentProps } from 'react-router-dom';
import { UserApi } from '../../api/UserApi';

interface IState {
    email: string;
    password: string;
}

class LoginPage extends React.Component<RouteComponentProps<any>, IState>
{

    constructor(props: RouteComponentProps) {
        super(props);
        this.state = { email: "", password: "" };
        this.onLogin = this.onLogin.bind(this);
    }
    render() {
        return (
            <div>
                <input type="text" name="email" value={this.state.email} onChange={this.updateEmail} />
                <input type="text" name="password" value={this.state.password} onChange={this.updatePassword} />
                <button onClick={this.onLogin}>Login</button>
            </div>
        );

    }

    updateEmail(evt: ChangeEvent) {
        var value = "";
        if (evt.target.nodeValue != null) {
            value = evt.target.nodeValue;
        }
        this.setState({
            email: value
        });
    }

    updatePassword(evt: ChangeEvent) {
        var value = "";
        if (evt.target.nodeValue != null) {
            value = evt.target.nodeValue;
        }
        this.setState({
            password: value
        });
    }


    onLogin() {
        UserApi.login(this.state.email, this.state.password);
    }
}

export default LoginPage;