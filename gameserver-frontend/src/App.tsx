import React from 'react';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import {
  Button,
  ButtonGroup
} from "react-bootstrap";
import 'bootstrap/dist/css/bootstrap.min.css';
import ExamplePage from './pages/example/ExamplePage';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Router>

          <div>
            <ButtonGroup>
              <Link to="/"><Button size="lg" variant="secondary">Home</Button></Link>
              <Link to="/example"><Button size="lg" variant="secondary">Example</Button></Link>
            </ButtonGroup >

            {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
            <Switch>
              <Route path="/example" render={(props) => <ExamplePage {...props} />} />
              <Route path="/">
                <Home />
              </Route>
            </Switch>
          </div>
        </Router>
      </header>

    </div>
  );
}

function Home() {
  return <h2>Home</h2>;
}


export default App;
