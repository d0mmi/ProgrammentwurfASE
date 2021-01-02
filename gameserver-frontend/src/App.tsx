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
import RegisterPage from './pages/login/RegisterPage';
import LoginPage from './pages/login/LoginPage';

function App() {
  return (
    <div className="App">
      <header className="App-header">
        <Router>

          <div>
            <ButtonGroup>
              <Link to="/"><Button size="lg" variant="secondary">Home</Button></Link>
              <Link to="/example"><Button size="lg" variant="secondary">Example</Button></Link>
              <Link to="/register"><Button size="lg" variant="secondary">Register</Button></Link>
              <Link to="/login"><Button size="lg" variant="secondary">Login</Button></Link>
            </ButtonGroup >

            {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
            <Switch>
              <Route path="/example" render={(props) => <ExamplePage {...props} />} />
              <Route path="/register" render={(props) => <RegisterPage {...props} />} />
              <Route path="/login" render={(props) => <LoginPage {...props} />} />
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
