import React from 'react';
import '../../App.css';
import { CircularProgress, Grid, IconButton, Paper, Theme, withStyles } from '@material-ui/core';
import DoneIcon from '@material-ui/icons/Done';
import BanIcon from '@material-ui/icons/Gavel';
import { Error } from '../../api/APIManager';
import { CellParams, ColDef, DataGrid } from '@material-ui/data-grid';
import { Report, ReportAPI } from '../../api/ReportAPI';

interface IState {
    reports: Report[];
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


class ReportListPage extends React.Component<any, IState>
{
    classes: any;

    constructor(props: any) {
        super(props);
        console.log(props);
        this.classes = this.props.classes;
        this.fetchReports = this.fetchReports.bind(this);
        this.onClick = this.onClick.bind(this);
        this.getReportFromParams = this.getReportFromParams.bind(this);

        this.state = { reports: [], loading: false };
    }
    columns: ColDef[] = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'creator', headerName: 'Creator', width: 100 },
        { field: 'reported', headerName: 'User', width: 90 },
        { field: 'reason', headerName: 'Reason', width: 250 },
        { field: 'type', headerName: 'Report Type', width: 250 },
        { field: 'open', headerName: 'Status', width: 100 },
        {
            field: "",
            headerName: "Manage",
            disableClickEventBubbling: true,
            width: 150,
            renderCell: (params: CellParams) => {

                return <div>
                    <IconButton onClick={(e) => this.onClick(params)} aria-label="add"><DoneIcon fontSize="small" /></IconButton>
                    <IconButton onClick={(e) => this.onClick(params)} aria-label="remove"><BanIcon fontSize="small" /></IconButton>
                </div>;
            }
        }
    ];

    onClick(params: CellParams) {
        var report = this.getReportFromParams(params);
        if (report !== null) {

        }
    }

    getReportFromParams(params: CellParams): Report | null {
        var index = params.rowIndex;
        if (index !== undefined) {
            var report = this.state.reports[index];
            return report;
        }
        return null;
    }

    render() {

        if (this.state.reports.length === 0) {
            if (!this.state.loading) {
                this.setState({ loading: true });
                this.fetchReports();
            }
            return (<CircularProgress />);
        }

        return (
            <Grid item xs={12} className={this.classes.card_grid} >
                <Grid container direction="row" justify="space-evenly" alignItems="center" spacing={0} >
                    <Grid key={1} item xs={"auto"}>
                        <Paper className={this.classes.paper}>
                            <DataGrid rows=
                                {this.state.reports.map((report: Report) => ({ id: report.id, creator: report.creator, reported: report.reported, reason: report.reason, type: report.type.name, open: report.open }))}
                                columns={this.columns} autoPageSize checkboxSelection />
                        </Paper>
                    </Grid>
                </Grid>
            </Grid>
        );

    }

    async fetchReports() {
        var response = await ReportAPI.getReports();

        if ((response as Error).message === undefined) {
            var reports = response as Report[];
            this.setState({
                reports: reports
            });
        }
    }


}

export default withStyles(styles, { withTheme: true })(ReportListPage);