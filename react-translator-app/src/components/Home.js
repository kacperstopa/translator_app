import React, {useEffect, useState} from 'react';
import {makeStyles} from '@material-ui/core/styles';
import Box from "@material-ui/core/Box";
import Divider from "@material-ui/core/Divider";
import Paper from "@material-ui/core/Paper";
import TextField from "@material-ui/core/TextField";
import {translationService} from "../services/translationService";
import Select from "@material-ui/core/Select";
import MenuItem from "@material-ui/core/MenuItem";
import Button from "@material-ui/core/Button";
import {languages} from "../constants/Languages";
import List from "@material-ui/core/List";
import ListItem from "@material-ui/core/ListItem";
import ListItemText from "@material-ui/core/ListItemText";

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

    useEffect(() => {
        getTranslationsHistory()
    }, []);

    const [fromText, setFromText] = useState("");
    const [fromLanguage, setFromLanguage] = useState("pl");

    const [toLanguage, setToLanguage] = useState("en");
    const [toText, setToText] = useState("");

    const [translationsHistory, setTranslationsHistory] = useState([])

    function translate() {
        translationService.translate(fromLanguage, toLanguage, fromText).then(response => {
            response.text().then(text => setToText(text))
        }).then(x =>
            getTranslationsHistory()
        )
    }

    function detect() {
        translationService.detectLanguage(fromText).then(response => {
            response.text().then(text => setFromLanguage(text))
        })
    }

    function getTranslationsHistory() {
        translationService.getTranslationsHistory().then(response => {
            response.text().then(text => setTranslationsHistory(JSON.parse(text)))
        })
    }

    function getKeyByValue(object, value) {
        return Object.keys(object).find(key => object[key] === value);
    }

    return (
        <div>
            <Box display="flex" flexDirection="row" justifyContent="center" styles={"width: 400px"}>
                <Paper className={classes.mainPaper}>
                    <Box display="flex" justifyContent="center" p={1} m={1}>
                        <Box p={1}>
                            <h1>Translator</h1>
                        </Box>
                    </Box>
                    <Divider/>
                    <Box display="flex" justifyContent="center" flexDirection="column" p={1} m={1}>
                        <Select
                            autoWidth
                            value={fromLanguage}
                            onChange={e => setFromLanguage(e.target.value)}
                            style={{width: "fit-content"}}
                        >
                            {Object.keys(languages).map((value, index) =>
                                <MenuItem value={languages[value]}>{value}</MenuItem>
                            )}
                        </Select>
                        <Box display="flex" justifyContent="center" p={1} m={1}>
                            <TextField
                                id="outlined-multiline-static"
                                value={fromText}
                                onChange={e => setFromText(e.target.value)}
                                label="From"
                                multiline
                                fullWidth
                                rows={4}
                                defaultValue="Default Value"
                                variant="outlined"
                            />
                            <Box display="flex" flexDirection="column" justifyContent="space-between" p={1} m={1}>
                                <Button
                                    color="primary"
                                    variant={"contained"}
                                    onClick={() => detect()}
                                >
                                    Detect
                                </Button>
                                <Button
                                    color="primary"
                                    variant={"contained"}
                                    onClick={() => translate()}
                                >
                                    Translate
                                </Button>
                            </Box>
                        </Box>
                    </Box>
                    <Divider/>
                    <Box display="flex" justifyContent="center" flexDirection="column" p={1} m={1}>
                        <Select
                            autoWidth
                            value={toLanguage}
                            onChange={e => setToLanguage(e.target.value)}
                            style={{width: "fit-content"}}
                        >
                            {Object.keys(languages).map((value, index) =>
                                <MenuItem value={languages[value]}>{value}</MenuItem>
                            )}
                        </Select>
                        <Box display="flex" justifyContent="center" p={1} m={1}>
                            <TextField
                                id="outlined-multiline-static"
                                label="To"
                                value={toText}
                                multiline
                                fullWidth
                                rows={4}
                                defaultValue="Default Value"
                                variant="outlined"
                            />
                        </Box>
                    </Box>
                    <Divider/>
                    <h2>Recent translations</h2>
                    <Box>
                        <List>
                            {
                                translationsHistory.map((value, index) =>
                                    <div>
                                        <ListItem>
                                            <ListItemText primary={value.fromText + " -> " + value.toText}
                                                          secondary={getKeyByValue(languages, value.from) + " -> " + getKeyByValue(languages, value.to)}
                                                          style={{overflow: "hidden", textOverflow: "ellipsis"}}
                                            />
                                        </ListItem>
                                        <Divider/>
                                    </div>
                                )
                            }
                        </List>
                    </Box>
                </Paper>
            </Box>
        </div>
    );
}

export default Home;
