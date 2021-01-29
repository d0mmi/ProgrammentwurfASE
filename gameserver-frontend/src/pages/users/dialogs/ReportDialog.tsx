import React from 'react';
import '../../../App.css';
import { Button, CircularProgress, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, FormControl, InputLabel, MenuItem, Select, TextField, Theme, withStyles } from '@material-ui/core';
import { ReportAPI, ReportType } from '../../../api/ReportAPI';
import { Error } from '../../../api/APIManager';

interface IState {
    reason: string;
    type: string;
    types: ReportType[]
    loading: boolean;
}

const styles = (theme: Theme) => ({
    root: {
    },
    formControl: {
        margin: theme.spacing(1),
        minWidth: 120,
    },
});


class ReportDialog extends React.Component<any, IState>
{
    classes: any;

    constructor(props: any) {
        super(props);
        console.log(props);
        this.classes = this.props.classes;
        this.fetchReportTypes = this.fetchReportTypes.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.reportUser = this.reportUser.bind(this);
        this.handleTypeChange = this.handleTypeChange.bind(this);

        this.state = { reason: "", type: "", types: [], loading: false };
    }

    render() {

        if (this.state.types.length === 0) {
            if (!this.state.loading) {
                this.setState({ loading: true });
                this.fetchReportTypes();
            }
            return (<CircularProgress />);
        }


        return (
            <Dialog open={this.props.open} onClose={this.handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Report User: {this.props.user?.name}</DialogTitle>
                <DialogContent>
                    <DialogContentText>To report this User, please enter the reason and report type here.</DialogContentText>
                    <TextField autoFocus margin="dense" id="name" label="Reason" fullWidth onInput={(e: React.ChangeEvent<HTMLInputElement>) => this.setState({ reason: e.target.value })} />
                    <FormControl variant="outlined" className={this.classes.formControl}>
                        <InputLabel id="demo-simple-select-outlined-label">Type</InputLabel>
                        <Select
                            labelId="demo-simple-select-outlined-label"
                            id="demo-simple-select-outlined"
                            value={this.state.type}
                            onChange={this.handleTypeChange}
                            label="Age">
                            {this.state.types.map((type: ReportType) => (<MenuItem value={type.name}>{type.name}</MenuItem>))}
                        </Select>
                    </FormControl>
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.handleClose} color="primary">Cancel</Button>
                    <Button onClick={this.reportUser} color="primary">Report</Button>
                </DialogActions>
            </Dialog>
        );

    }

    handleTypeChange(event: React.ChangeEvent<{ value: unknown }>) {
        this.setState({
            type: event.target.value as string
        });
    }

    handleClose() {
        this.setState({
            type: "",
            reason: ""
        });
        this.props.close();
    }

    async fetchReportTypes() {
        var response = await ReportAPI.getReportTypes();

        if ((response as Error).message === undefined) {
            var types = response as ReportType[];
            this.setState({
                types: types
            });
        }
    }

    async reportUser() {
        if (this.state.reason.length > 0 && this.state.type.length > 0) {
            ReportAPI.report(this.props.user.id, this.state.reason, this.state.type);
        }
        this.handleClose();
    }


}

export default withStyles(styles, { withTheme: true })(ReportDialog);