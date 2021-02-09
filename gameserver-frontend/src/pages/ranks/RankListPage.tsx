import React from 'react';
import '../../App.css';
import { CircularProgress, Grid, Paper, Theme, withStyles } from '@material-ui/core';
import { Error } from '../../api/APIManager';
import { ColDef, DataGrid } from '@material-ui/data-grid';
import { Rank, RankAPI } from '../../api/RankAPI';

interface IState {
    ranks: Rank[];
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
        this.fetchRanks = this.fetchRanks.bind(this);

        this.state = { ranks: [], loading: false };
    }
    columns: ColDef[] = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'name', headerName: 'Name', width: 100 },
        { field: 'level', headerName: 'Level', width: 90 }
    ];

    render() {

        if (this.state.ranks.length === 0) {
            if (!this.state.loading) {
                this.setState({ loading: true });
                this.fetchRanks();
            }
            return (<CircularProgress />);
        }

        return (
            <Grid item xs={12} className={this.classes.card_grid} >
                <Grid container direction="row" justify="space-evenly" alignItems="center" spacing={0} >
                    <Grid key={1} item xs={"auto"}>
                        <Paper className={this.classes.paper}>
                            <DataGrid rows=
                                {this.state.ranks.map((rank: Rank, index: number) => ({ id: index, name: rank.name, level: rank.level }))}
                                columns={this.columns} autoPageSize checkboxSelection />
                        </Paper>
                    </Grid>
                </Grid>
            </Grid>
        );

    }

    async fetchRanks() {
        var response = await RankAPI.getRanks();

        if ((response as Error).message === undefined) {
            var ranks = response as Rank[];
            this.setState({
                ranks: ranks
            });
        }
    }


}

export default withStyles(styles, { withTheme: true })(BanListPage);