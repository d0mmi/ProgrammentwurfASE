import React from 'react';
import './App.css';
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import 'fontsource-roboto';
import ExamplePage from './pages/example/ExamplePage';
import LoginPage from './pages/login/LoginPage';
import { AppBar, Button, createMuiTheme, createStyles, IconButton, makeStyles, Theme, ThemeProvider, Toolbar, Typography } from '@material-ui/core';
import { MenuIcon } from '@material-ui/data-grid';

const darkTheme = createMuiTheme({
  palette: {
    type: "dark",
    background: {
      default: "#282c34"
    }
  }
});
const lightTheme = createMuiTheme({
  palette: {
    type: "light",
  }
});

function App() {
  const useStyles = makeStyles((theme: Theme) =>
    createStyles({
      header: {
        background: darkTheme.palette.background.default,
        color: "white"
      },
      root: {
      },
      menuButton: {
        marginRight: theme.spacing(2),
      },
      title: {
        flexGrow: 1,
      },
      appbar: {
        top: 0
      },
      navButton: {
        margin: "1px",
        color: "white",
        textDecoration: "none"
      }
    }));

  const classes = useStyles();

  return (
    <ThemeProvider theme={darkTheme}>
      <div className="App">
        <header className={classes.header + " App-header"}>
          <Router>
            <AppBar position="fixed" className={classes.appbar}>
              <Toolbar>
                <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
                  <MenuIcon />
                </IconButton>
                <Typography variant="h6" className={classes.title}>
                  {/*TODO Add Name*/}
                </Typography>
                <Link to="/"><Button color="inherit" className={classes.navButton}>Home</Button></Link>
                <Link to="/login"><Button color="inherit" className={classes.navButton}>Login</Button></Link>
              </Toolbar>
            </AppBar>

            {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
            <Switch>
              <Route path="/example" component={ExamplePage} />
              <Route path="/login" component={LoginPage} />
              <Route path="/" component={Home} />
            </Switch>
          </Router>
        </header>
      </div>
    </ThemeProvider>
  );
}

function Home() {
  return <h2>Home</h2>;
}


export default App;
