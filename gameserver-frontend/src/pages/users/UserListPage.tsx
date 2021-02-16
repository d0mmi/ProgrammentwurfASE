import React from 'react';
import '../../App.css';
import { UserApi, User } from '../../api/UserApi';
import { CircularProgress, Grid, IconButton, Paper, Theme, withStyles } from '@material-ui/core';
import ReportIcon from '@material-ui/icons/Report';
import BanIcon from '@material-ui/icons/Gavel';
import RankIcon from '@material-ui/icons/Security';
import { Error } from '../../api/APIManager';
import { CellParams, ColDef, DataGrid } from '@material-ui/data-grid';
import RankDialog from '../../dialogs/RankDialog';
import ReportDialog from '../../dialogs/ReportDialog';
import BanDialog from '../../dialogs/BanDialog';

interface IState {
    selectedUser: User | null;
    rankState: boolean;
    reportState: boolean;
    banState: boolean;
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


class UserListPage extends React.Component<any, IState>
{
    classes: any;

    constructor(props: any) {
        super(props);
        console.log(props);
        this.classes = this.props.classes;
        this.fetchUsers = this.fetchUsers.bind(this);
        this.onRank = this.onRank.bind(this);
        this.onRankClose = this.onRankClose.bind(this);
        this.onReport = this.onReport.bind(this);
        this.onReportClose = this.onReportClose.bind(this);
        this.onBan = this.onBan.bind(this);
        this.onBanClose = this.onBanClose.bind(this);
        this.getUserFromParams = this.getUserFromParams.bind(this);

        this.state = { selectedUser: null, rankState: false, reportState: false, banState: false, users: [], loading: false };
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
                    <IconButton onClick={(e) => this.onRank(params)} aria-label="rank"><RankIcon fontSize="small" /></IconButton>
                    <IconButton onClick={(e) => this.onReport(params)} aria-label="report"><ReportIcon fontSize="small" /></IconButton>
                    <IconButton onClick={(e) => this.onBan(params)} aria-label="ban"><BanIcon fontSize="small" /></IconButton>
                </div>;
            }
        }
    ];

    onRank(params: CellParams) {
        var user = this.getUserFromParams(params);
        if (user !== undefined) {
            this.setState({
                selectedUser: user,
                rankState: true
            });
        }
    }

    onRankClose() {
        this.setState({
            rankState: false
        });
    }


    onReport(params: CellParams) {
        var user = this.getUserFromParams(params);
        if (user !== undefined) {
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
        if (user !== undefined) {
            this.setState({
                selectedUser: user,
                banState: true
            });
        }
    }

    onBanClose() {
        this.setState({
            banState: false
        });
    }

    getUserFromParams(params: CellParams): User | undefined {
        var id = params.getValue('id')?.toString();
        if (id !== undefined) {
            var idNumber = +id;
            var user = this.state.users.find((user) => user.id == idNumber);
            return user;
        }
        return undefined;
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

                            <RankDialog user={this.state.selectedUser} open={this.state.rankState} close={this.onRankClose} />
                            <ReportDialog user={this.state.selectedUser} open={this.state.reportState} close={this.onReportClose} />
                            <BanDialog user={this.state.selectedUser} open={this.state.banState} close={this.onBanClose} />
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

export default withStyles(styles, { withTheme: true })(UserListPage);