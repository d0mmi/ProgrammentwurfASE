import React from 'react';
import {
    Button
} from "react-bootstrap";
import '../../App.css';
import { RouteComponentProps } from 'react-router-dom';

interface IState {
    loaded: boolean;
    data: any;
}

class ExamplePage extends React.Component<RouteComponentProps<any>, IState>
{
    constructor(props: RouteComponentProps) {
        super(props);
        this.state = { loaded: false, data: {} };
    }
    render() {
        if (!this.state.loaded) {
            this.getExample();
            return (
                <div>Loading..</div>
            );
        } else {
            return (
                <div>Loaded! Value: {this.state.data}</div>

            );
        }

    }

    async getExample() {

    }

}

export default ExamplePage;