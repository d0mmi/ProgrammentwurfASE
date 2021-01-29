import React from 'react';
import '../../App.css';
import { UserApi, User } from '../../api/UserApi';
import { CircularProgress, Grid, IconButton, Paper, Theme, withStyles } from '@material-ui/core';
import ReportIcon from '@material-ui/icons/Report';
import BanIcon from '@material-ui/icons/Gavel';
import { withCookies } from 'react-cookie';
import { Error } from '../../api/APIManager';
import { CellParams, ColDef, DataGrid } from '@material-ui/data-grid';
import ReportDialog from './dialogs/ReportDialog';

interface IState {
    selectedUser: User | null;
    reportState: boolean;
    users: User[];
    loading: boolean;
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
        marginBottom: '25px',
        padding: '25px',
        height: "60vh",
        width: "80vw"
    },
    input: {
        marginTop: "15px"
    },
});


class LoginPage extends React.Component<any, IState>
{
    classes: any;

    constructor(props: any) {
        super(props);
        console.log(props);
        this.classes = this.props.classes;
        this.fetchUsers = this.fetchUsers.bind(this);
        this.onReport = this.onReport.bind(this);
        this.onReportClose = this.onReportClose.bind(this);
        this.onBan = this.onBan.bind(this);
        this.getUserFromParams = this.getUserFromParams.bind(this);

        this.state = { selectedUser: null, reportState: false, users: [], loading: false };
    }

    columns: ColDef[] = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'name', headerName: 'Name', width: 250 },
        { field: 'email', headerName: 'Email', width: 250 },
        { field: 'level', headerName: 'Permission Level', width: 250 },
        {
            field: "",
            headerName: "Manage",
            disableClickEventBubbling: true,
            width: 150,
            renderCell: (params: CellParams) => {

                return <div>
                    <IconButton onClick={(e) => this.onReport(params)} aria-label="add"><ReportIcon fontSize="small" /></IconButton>
                    <IconButton onClick={(e) => this.onBan(params)} aria-label="remove"><BanIcon fontSize="small" /></IconButton>
                </div>;
            }
        }
    ];

    onReport(params: CellParams) {
        var user = this.getUserFromParams(params);
        if (user !== null) {
            this.setState({
                selectedUser: user,
                reportState: true
            });
        }
    }

    onReportClose() {
        this.setState({
            reportState: false
        });
    }

    onBan(params: CellParams) {
        var user = this.getUserFromParams(params);
        if (user !== null) {

        }
    }

    getUserFromParams(params: CellParams): User | null {
        var index = params.rowIndex;
        if (index !== undefined) {
            var user = this.state.users[index];
            return user;
        }
        return null;
    }

    render() {

        if (this.state.users.length === 0) {
            if (!this.state.loading) {
                this.setState({ loading: true });
                this.fetchUsers();
            }
            return (<CircularProgress />);
        }

        return (
            <Grid item xs={12} className={this.classes.card_grid} >
                <Grid container direction="row" justify="space-evenly" alignItems="center" spacing={0} >
                    <Grid key={1} item xs={"auto"}>
                        <Paper className={this.classes.paper}>
                            <DataGrid rows=
                                {this.state.users.map((user: User) => ({ id: user.id, name: user.name, email: user.email, level: user.level }))}
                                columns={this.columns} autoPageSize checkboxSelection />

                            <ReportDialog user={this.state.selectedUser} open={this.state.reportState} close={this.onReportClose} />
                        </Paper>
                    </Grid>
                </Grid>
            </Grid>
        );

    }

    async fetchUsers() {
        var response = await UserApi.getAll();

        if ((response as Error).message === undefined) {
            var users = response as User[];
            this.setState({
                users: users
            });
        }
    }


}

export default withStyles(styles, { withTheme: true })(withCookies(LoginPage));