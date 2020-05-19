import React, {useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Box from "@material-ui/core/Box";
import Divider from "@material-ui/core/Divider";
import Paper from "@material-ui/core/Paper";
import TextField from "@material-ui/core/TextField";
import IconButton from "@material-ui/core/IconButton";
import PlayCircleFilledWhiteIcon from '@material-ui/icons/PlayCircleFilledWhite';
import {translationService} from "../services/translationService";

const useStyles = makeStyles(theme => ({
    button: {
        margin: theme.spacing(1),
    },

    root: {
        flexGrow: 1,
    },

    paper: {
        padding: '20px'
    },

    mainPaper: {
        padding: '20px',
        margin: '20px',
        width: '40%'
    }
}));


function Home() {
    const classes = useStyles();

    const [fromText, setFromText] = useState("");
    const [toText, setToText] = useState("");


    function translate() {
        translationService.translate(fromText).then(response => {
            response.text().then(text => setToText(text))
        })
    }

    return (
        <div>
            <Box display="flex" flexDirection="row" justifyContent="center" styles={"width: 400px"}>
                <Paper className={classes.mainPaper}>
                    <Box display="flex" justifyContent="center" p={1} m={1}>
                        <Box p={1}>
                            Translator
                        </Box>
                    </Box>
                    <Divider/>
                    <Box display="flex" justifyContent="center" p={1} m={1}>
                        <TextField
                            id="outlined-multiline-static"
                            value={fromText}
                            onChange={e => setFromText(e.target.value)}
                            label="Multiline"
                            multiline
                            fullWidth
                            rows={4}
                            defaultValue="Default Value"
                            variant="outlined"
                        />
                        <Box display="flex" flexDirection="row" justifyContent="flex-end" p={1} m={1}>
                            <IconButton
                                onClick={() => translate()}
                                color="primary" className={classes.iconButton} aria-label="directions">
                                <PlayCircleFilledWhiteIcon/>
                            </IconButton>
                        </Box>
                    </Box>
                    <Divider/>
                    <Box display="flex" justifyContent="center" p={1} m={1}>
                        <TextField
                            id="outlined-multiline-static"
                            label="Multiline"
                            value={toText}
                            multiline
                            fullWidth
                            rows={4}
                            defaultValue="Default Value"
                            variant="outlined"
                        />
                    </Box>
                </Paper>
            </Box>
        </div>
    );
}

export default Home;
