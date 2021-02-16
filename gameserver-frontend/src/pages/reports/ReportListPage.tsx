import React from 'react';
import '../../App.css';
import { CircularProgress, Grid, IconButton, Paper, Theme, withStyles } from '@material-ui/core';
import DoneIcon from '@material-ui/icons/Done';
import ClearIcon from '@material-ui/icons/Clear';
import BanIcon from '@material-ui/icons/Gavel';
import { Error } from '../../api/APIManager';
import { CellParams, ColDef, DataGrid } from '@material-ui/data-grid';
import { Report, ReportAPI } from '../../api/ReportAPI';
import BanDialog from '../../dialogs/BanDialog';
import { User } from '../../api/UserApi';

interface IState {
    selectedUser: User | null;
    banState: boolean;
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
        this.classes = this.props.classes;
        this.fetchReports = this.fetchReports.bind(this);
        this.onResolve = this.onResolve.bind(this);
        this.onBan = this.onBan.bind(this);
        this.onBanClose = this.onBanClose.bind(this);
        this.getReportFromParams = this.getReportFromParams.bind(this);

        this.state = { selectedUser: null, banState: false, reports: [], loading: false };
    }
    columns: ColDef[] = [
        { field: 'id', headerName: 'ID', width: 70 },
        { field: 'creator', headerName: 'Creator', width: 100 },
        { field: 'reported', headerName: 'User', width: 90 },
        { field: 'reason', headerName: 'Reason', width: 250 },
        { field: 'type', headerName: 'Report Type', width: 250 },
        { field: 'open', headerName: 'Status', width: 125 },
        {
            field: "",
            headerName: "Manage",
            disableClickEventBubbling: true,
            width: 150,
            renderCell: (params: CellParams) => {
                var icon = <DoneIcon fontSize="small" />;
                var report = this.getReportFromParams(params);
                if (report !== undefined) {
                    if (!report.open) {
                        icon = <ClearIcon fontSize="small" />;
                    }
                }
                return <div>
                    <IconButton onClick={(e) => this.onResolve(params)} aria-label="add">{icon}</IconButton>
                    <IconButton onClick={(e) => this.onBan(params)} aria-label="remove"><BanIcon fontSize="small" /></IconButton>
                </div>;
            }
        }
    ];

    onResolve(params: CellParams) {
        var report = this.getReportFromParams(params);
        if (report !== undefined) {
            ReportAPI.updateReportStatus(report.id, !report.open);
            report.open = !report.open;
            this.setState({});
        }
    }

    async onBan(params: CellParams) {
        var report = this.getReportFromParams(params);
        if (report !== undefined) {
            this.setState({
                selectedUser: report.reported,
                banState: true
            });
        }
    }

    onBanClose() {
        this.setState({
            banState: false
        });
    }

    getReportFromParams(params: CellParams): Report | undefined {
        var id = params.getValue('id')?.toString();
        if (id !== undefined) {
            var idNumber = +id;
            var report = this.state.reports.find((report) => report.id === idNumber);
            return report;
        }
        return undefined;
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
                                {this.state.reports.map((report: Report) => ({ id: report.id, creator: report.creator.name, reported: report.reported.name, reason: report.reason, type: report.type.name, open: report.open ? "Open" : "Closed" }))}
                                columns={this.columns} autoPageSize checkboxSelection />

                            <BanDialog user={this.state.selectedUser} open={this.state.banState} close={this.onBanClose} />
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