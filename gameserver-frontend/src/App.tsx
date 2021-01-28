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
import { AppBar, Button, createMuiTheme, createStyles, Drawer, IconButton, List, ListItem, ListItemIcon, ListItemText, makeStyles, Theme, ThemeProvider, Toolbar, Typography } from '@material-ui/core';
import PersonIcon from '@material-ui/icons/Person';
import ReportIcon from '@material-ui/icons/Report';
import BanIcon from '@material-ui/icons/Gavel';
import RankIcon from '@material-ui/icons/Security';
import { MenuIcon } from '@material-ui/data-grid';
import { useCookies } from 'react-cookie';
import { APIManager } from './api/APIManager';
import ProfilePage from './pages/profile/ProfilePage';
import { User } from './api/UserApi';
import UserListPage from './pages/users/UserListPage';

const darkTheme = createMuiTheme({
  palette: {
    type: "dark",
    background: {
      default: "#282c34"
    }
  }
});

function App() {

  const useStyles = makeStyles((theme: Theme) =>
    createStyles({
      header: {
        background: darkTheme.palette.background.default,
        color: "white"
      },
    }));

  const classes = useStyles();


  return (
    <ThemeProvider theme={darkTheme}>
      <div className="App">
        <header className={classes.header + " App-header"}>
          <Router>
            <SideBar />
            {/* A <Switch> looks through its children <Route>s and
            renders the first one that matches the current URL. */}
            <Switch>
              <Route path="/example" component={ExamplePage} />
              <Route path="/login" component={LoginPage} />
              <Route path="/users" component={UserListPage} />
              <Route exact path="/" component={Home} />
            </Switch>
          </Router>
        </header>
      </div>
    </ThemeProvider>
  );
}

function Home() {
  const [cookies] = useCookies(['session']);
  if (cookies['session'] === undefined) {
    return <h2>Home</h2>;
  }
  return <ProfilePage />;
}

function SideBar() {
  const [cookies] = useCookies(["session"]);
  const [userCookies] = useCookies(["user"]);

  const [state, setState] = React.useState({
    open: false
  });

  const toggleState = (openState: boolean) => {
    setState({ open: openState })
  }

  const useStyles = makeStyles((theme: Theme) =>
    createStyles({
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
      },
      drawer: {
        width: "200px"
      }
    }));

  const classes = useStyles();

  function isTokenNotSet() {
    return APIManager.token === null || APIManager.token === undefined;
  }

  if (isTokenNotSet()) APIManager.token = cookies['session'];

  var user: User = userCookies['user'];
  var content: any[] = [];
  if (user !== null && user !== undefined) {
    if (user.level >= 50) {
      content.push(<ListItem button key={1}><ListItemIcon><PersonIcon /></ListItemIcon><ListItemText primary={"Users"} /></ListItem>);
      content.push(<ListItem button key={2}><ListItemIcon><ReportIcon /></ListItemIcon><ListItemText primary={"Reports"} /></ListItem>);
      content.push(<ListItem button key={3}><ListItemIcon><BanIcon /></ListItemIcon><ListItemText primary={"Bans"} /></ListItem>);
    }
    if (user.level >= 100) {
      content.push(<ListItem button key={4}><ListItemIcon><RankIcon /></ListItemIcon><ListItemText primary={"Ranks"} /></ListItem>);
    }

  }

  return (
    <div>
      <AppBar position="fixed" className={classes.appbar}>
        <Toolbar>
          <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu" onClick={() => toggleState(true)}>
            <MenuIcon />
          </IconButton>
          <Typography variant="h6" className={classes.title}>
            {/*TODO Add Name*/}
          </Typography>
          <Link to="/"><Button color="inherit" className={classes.navButton}>Home</Button></Link>
          {
            isTokenNotSet() ? <Link to="/login"><Button color="inherit" className={classes.navButton}>Login</Button></Link> : <div />
          }
        </Toolbar>
      </AppBar>
      <Drawer anchor="left" open={state.open} onClose={() => toggleState(false)}>
        <List className={classes.drawer}>
          {content}
        </List>
      </Drawer>
    </div>
  );

}


export default App;
