import React, {useState} from "react";
import Container from "@material-ui/core/Container";
import CssBaseline from "@material-ui/core/CssBaseline";
import Typography from "@material-ui/core/Typography";
import TextField from "@material-ui/core/TextField";
import FormControlLabel from "@material-ui/core/FormControlLabel";
import Button from "@material-ui/core/Button";
import Grid from "@material-ui/core/Grid";
import Link from "@material-ui/core/Link";
import Checkbox from "@material-ui/core/Checkbox";
import makeStyles from "@material-ui/core/styles/makeStyles";
import Axios from "axios";
import {useHistory} from "react-router";
import Dialog from "@material-ui/core/Dialog";
import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import DialogContentText from "@material-ui/core/DialogContentText";
import DialogActions from "@material-ui/core/DialogActions";

export default function SignIn() {

    const history = useHistory();

    const [param, setParam] = useState({username: "", password: ""});

    const classes = makeStyles(theme => ({
        paper: {
            marginTop: theme.spacing(8),
            display: 'flex',
            flexDirection: 'column',
            alignItems: 'center',
        },
        avatar: {
            margin: theme.spacing(1),
            backgroundColor: theme.palette.secondary.main,
        },
        form: {
            width: '100%', // Fix IE 11 issue.
            marginTop: theme.spacing(1),
        },
        submit: {
            margin: theme.spacing(3, 0, 2),
        },
    }))();

    function usernameOnChange(event) {
        param.username = event.target.value;
        setParam({
            ...param
        })
    }

    function passwordOnChange(event) {
        param.password = event.target.value;
        setParam({
            ...param
        })
    }

    function signIn() {
        let data = new FormData();
        data.append("username", param.username);
        data.append("password", param.password);

        Axios({
            url: "/login",
            method: "post",
            data: data
        }).then((response) => {
            history.push("/ping")
        }).catch(ex => {
            setOpen(true)
        })
    }

    const [open, setOpen] = useState(false);

    function handleClose() {
        setOpen(false);
    }

    return (
        <Container component="main" maxWidth="xs">
            <CssBaseline/>
            <div className={classes.paper}>
                <Typography component="h1" variant="h5">
                    Sign in
                </Typography>
                <form className={classes.form} noValidate>
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        id="username"
                        label="Username"
                        name="username"
                        autoComplete="username"
                        autoFocus
                        value={param.username}
                        onChange={usernameOnChange}
                    />
                    <TextField
                        variant="outlined"
                        margin="normal"
                        required
                        fullWidth
                        name="password"
                        label="Password"
                        type="password"
                        id="password"
                        autoComplete="current-password"
                        value={param.password}
                        onChange={passwordOnChange}
                    />
                    <FormControlLabel
                        control={<Checkbox value="remember" color="primary"/>}
                        label="Remember me"
                    />
                    <Button
                        fullWidth
                        variant="contained"
                        color="primary"
                        className={classes.submit}
                        onClick={signIn}
                    >
                        Sign In
                    </Button>
                    <Grid container>
                        <Grid item xs>
                            <Link href="#" variant="body2">
                                Forgot password?
                            </Link>
                        </Grid>
                        <Grid item>
                            <Link href="#" variant="body2">
                                {"Don't have an account? Sign Up"}
                            </Link>
                        </Grid>
                    </Grid>
                </form>
            </div>
            <div>
                <Dialog
                    open={open}
                    onClose={handleClose}
                    aria-labelledby="alert-dialog-title"
                    aria-describedby="alert-dialog-description"
                >
                    <DialogTitle id="alert-dialog-title">{"Login Failure"}</DialogTitle>
                    <DialogContent>
                        <DialogContentText id="alert-dialog-description">
                            user: user
                            <br/>
                            password: [is random]
                        </DialogContentText>
                    </DialogContent>
                    <DialogActions>
                        <Button onClick={handleClose} color="primary">
                            Close
                        </Button>
                    </DialogActions>
                </Dialog>
            </div>
        </Container>
    );
}