import React from 'react';
import '../App.css';
import { Button, CircularProgress, Dialog, DialogActions, DialogContent, DialogContentText, DialogTitle, FormControl, InputLabel, MenuItem, Select, TextField, Theme, withStyles } from '@material-ui/core';
import { Rank, RankAPI } from '../api/RankAPI';
import { Error } from '../api/APIManager';

interface IState {
    rank: string;
    ranks: Rank[]
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


class RankDialog extends React.Component<any, IState>
{
    classes: any;

    constructor(props: any) {
        super(props);
        console.log(props);
        this.classes = this.props.classes;
        this.fetchRanks = this.fetchRanks.bind(this);
        this.handleClose = this.handleClose.bind(this);
        this.grantRank = this.grantRank.bind(this);
        this.handleRankChange = this.handleRankChange.bind(this);

        this.state = { rank: "", ranks: [], loading: false };
    }

    render() {

        if (this.state.ranks.length === 0) {
            if (!this.state.loading) {
                this.setState({ loading: true });
                this.fetchRanks();
            }
            return (<CircularProgress />);
        }


        return (
            <Dialog open={this.props.open} onClose={this.handleClose} aria-labelledby="form-dialog-title">
                <DialogTitle id="form-dialog-title">Set User Rank: {this.props.user?.name}</DialogTitle>
                <DialogContent>
                    <DialogContentText>To set the Rank of this User, please select the Rank below.</DialogContentText>
                    <FormControl variant="outlined" className={this.classes.formControl}>
                        <InputLabel id="demo-simple-select-outlined-label">Type</InputLabel>
                        <Select
                            labelId="demo-simple-select-outlined-label"
                            id="demo-simple-select-outlined"
                            value={this.state.rank}
                            onChange={this.handleRankChange}
                            label="Age">
                            {this.state.ranks.map((rank: Rank) => (<MenuItem value={rank.name}>{rank.name}</MenuItem>))}
                        </Select>
                    </FormControl>
                </DialogContent>
                <DialogActions>
                    <Button onClick={this.handleClose} color="primary">Cancel</Button>
                    <Button onClick={this.grantRank} color="primary">Set Rank</Button>
                </DialogActions>
            </Dialog>
        );

    }

    handleRankChange(event: React.ChangeEvent<{ value: unknown }>) {
        this.setState({
            rank: event.target.value as string
        });
    }

    handleClose() {
        this.setState({
            rank: ""
        });
        this.props.close();
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

    async grantRank() {
        if (this.state.rank.length > 0) {
            var rank = this.state.ranks.find((rank) => rank.name === this.state.rank);
            if (rank !== undefined) {
                RankAPI.grantRankTo(this.props.user.id, rank.name);
            }
        }
        this.handleClose();
    }


}

export default withStyles(styles, { withTheme: true })(RankDialog);