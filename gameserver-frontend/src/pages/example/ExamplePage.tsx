import React from 'react';
import {
    Button
} from "react-bootstrap";
import '../../App.css';
import { RouteComponentProps } from 'react-router-dom';
import { ExampleClient } from '../../proto/ExampleServiceClientPb';
import { ExampleRequest, ExampleResponse } from '../../proto/example_pb';

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

        var client = new ExampleClient('http://localhost:50051', null, null);
        const request = new ExampleRequest();
        var response = await client.getValue(request, {});

        this.setState({ data: response.getValue(), loaded: true });
    }

}

export default ExamplePage;