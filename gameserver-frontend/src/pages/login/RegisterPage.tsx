import React, { ChangeEvent } from 'react';
import {
    Button
} from "react-bootstrap";
import '../../App.css';
import { RouteComponentProps } from 'react-router-dom';
import { UserApi } from '../../api/UserApi';

interface IState {
    name: string;
    email: string;
    password: string;
}

class RegisterPage extends React.Component<RouteComponentProps<any>, IState>
{

    constructor(props: RouteComponentProps) {
        super(props);
        this.state = { name: "", email: "", password: "" };
        this.onRegister = this.onRegister.bind(this);
    }
    render() {
        return (
            <div>
                <input type="text" name="name" value={this.state.name} onChange={this.updateName} />
                <input type="text" name="email" value={this.state.email} onChange={this.updateEmail} />
                <input type="text" name="password" value={this.state.password} onChange={this.updatePassword} />
                <button onClick={this.onRegister}>Register</button>
            </div>
        );

    }

    updateName(evt: ChangeEvent) {
        var value = "";
        if (evt.target.nodeValue != null) {
            value = evt.target.nodeValue;
        }
        this.setState({
            name: value
        });
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


    onRegister() {
        UserApi.register(this.state.name, this.state.email, this.state.password);
    }
}

export default RegisterPage;