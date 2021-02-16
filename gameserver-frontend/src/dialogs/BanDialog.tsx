import React from 'react';
import '../App.css';
import { Button, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, TextField, Theme, withStyles } from '@material-ui/core';
import { KeyboardDatePicker, MuiPickersUtilsProvider } from '@material-ui/pickers';
import DateFnsUtils from '@date-io/date-fns';
import { BanAPI } from '../api/BanAPI';

interface IState {
    reason: string;
    until: Date | null;
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
        this.classes = this.props.classes;
        this.handleClose = this.handleClose.bind(this);
        this.banUser = this.banUser.bind(this);

        this.state = { reason: "", until: new Date() };
    }

    render() {

        return (
            <Dialog open={this.props.open} onClose={this.handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Ban User: {this.props.user?.name}</DialogTitle>
                <DialogContent>
                    <DialogContentText>To ban this User, please enter the reason and end date here.</DialogContentText>
                    <TextField autoFocus margin="dense" id="name" label="Reason" fullWidth onInput={(e: React.ChangeEvent<HTMLInputElement>) => this.setState({ reason: e.target.value })} />
                    <MuiPickersUtilsProvider utils={DateFnsUtils}>
                        <KeyboardDatePicker
                            disableToolbar
                            variant="inline"
                            format="dd/MM/yyyy"
                            margin="normal"
                            id="date-picker-inline"
                            label="Banned until"
                            value={this.state.until}
                            onChange={(date: Date | null) => this.setState({ until: date })}
                            KeyboardButtonProps={{
                                'aria-label': 'change date',
                            }}
                        />
                    </MuiPickersUtilsProvider>
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.handleClose} color="primary">Cancel</Button>
                    <Button onClick={this.banUser} color="primary">Ban</Button>
                </DialogActions>
            </Dialog>
        );

    }

    handleClose() {
        this.setState({
            reason: "",
            until: new Date()
        });
        this.props.close();
    }

    async banUser() {
        if (this.state.reason.length > 0 && this.state.until !== null) {
            BanAPI.ban(this.props.user.id, this.state.reason, this.state.until.toUTCString());
        }
        this.handleClose();
    }


}

export default withStyles(styles, { withTheme: true })(ReportDialog);