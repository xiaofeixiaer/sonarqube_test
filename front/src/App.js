import React from 'react';
import './App.css';
import Ping from "./component/ping/Ping";
import SignIn from "./component/signin/SignIn";
import {HashRouter, Switch, Route} from "react-router-dom";

function App() {
    return (
        <HashRouter>
            <div>
                <Switch>
                    <Route path={["/ping"]} component={Ping}/>
                    <Route path={["/signIn"]} component={SignIn}/>
                </Switch>
            </div>
        </HashRouter>
    )
}

export default App;
