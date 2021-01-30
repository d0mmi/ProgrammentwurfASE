import React from 'react';
import '../../App.css';
import { CircularProgress, Grid, IconButton, Paper, Theme, withStyles } from '@material-ui/core';
import DoneIcon from '@material-ui/icons/Done';
import { Error } from '../../api/APIManager';
import { CellParams, ColDef, DataGrid } from '@material-ui/data-grid';
import { Ban, BanAPI } from '../../api/BanAPI';

interface IState {
    bans: Ban[];
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


class BanListPage extends React.Component<any, IState>
{
    classes: any;

    constructor(props: any) {
        super(props);
        console.log(props);
        this.classes = this.props.classes;
        this.fetchBans = this.fetchBans.bind(this);
        this.onClick = this.onClick.bind(this);
        this.getBanFromParams = this.getBanFromParams.bind(this);

        this.state = { bans: [], loading: false };
    }
    columns: ColDef[] = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'userId', headerName: 'User', width: 100 },
        { field: 'bannedById', headerName: 'Banned By', width: 90 },
        { field: 'reason', headerName: 'Reason', width: 250 },
        { field: 'until', headerName: 'Until Date', width: 250 },
        { field: 'active', headerName: 'Status', width: 100 },
        {
            field: "",
            headerName: "Manage",
            disableClickEventBubbling: true,
            width: 150,
            renderCell: (params: CellParams) => {

                return <div>
                    <IconButton onClick={(e) => this.onClick(params)} aria-label="add"><DoneIcon fontSize="small" /></IconButton>
                </div>;
            }
        }
    ];

    onClick(params: CellParams) {
        var ban = this.getBanFromParams(params);
        if (ban !== null) {

        }
    }

    getBanFromParams(params: CellParams): Ban | null {
        var index = params.rowIndex;
        if (index !== undefined) {
            var ban = this.state.bans[index];
            return ban;
        }
        return null;
    }

    render() {

        if (this.state.bans.length === 0) {
            if (!this.state.loading) {
                this.setState({ loading: true });
                this.fetchBans();
            }
            return (<CircularProgress />);
        }

        return (
            <Grid item xs={12} className={this.classes.card_grid} >
                <Grid container direction="row" justify="space-evenly" alignItems="center" spacing={0} >
                    <Grid key={1} item xs={"auto"}>
                        <Paper className={this.classes.paper}>
                            <DataGrid rows=
                                {this.state.bans.map((ban: Ban) => ({ id: ban.id, userId: ban.userId, bannedById: ban.bannedById, reason: ban.reason, until: ban.until, active: ban.active }))}
                                columns={this.columns} autoPageSize checkboxSelection />
                        </Paper>
                    </Grid>
                </Grid>
            </Grid>
        );

    }

    async fetchBans() {
        var response = await BanAPI.getBans();

        if ((response as Error).message === undefined) {
            var bans = response as Ban[];
            this.setState({
                bans: bans
            });
        }
    }


}

export default withStyles(styles, { withTheme: true })(BanListPage);