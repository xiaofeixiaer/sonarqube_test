import React, {useEffect, useState} from "react";
import Axios from "axios";
import Input from "@material-ui/core/Input";
import Button from "@material-ui/core/Button";

function Ping() {

    const [currentState, setCurrentState] = useState({
        routes: [],
        host: "",
        ttl: 0
    });

    function next() {
        const {host, ttl} = currentState;

        if (host === "") {
            return
        }

        Axios.post("/ping", {
            host: host,
            ttl: ttl
        }).then((response) => {
            const {routes, host, ttl} = currentState;
            routes.push(response.data);

            setCurrentState({
                routes: routes,
                ip: host,
                ttl: ttl + 1
            });
        }).catch((e) => {
            console.log(e)
        })
    }

    useEffect(next, [currentState.ttl]);

    function start() {
        setCurrentState({...currentState, ttl: 1})
    }

    function extracted() {
        return (
            <p>{JSON.stringify(currentState.routes)}</p>
        )
    }

    function handleInput(event) {
        currentState.ip = event.target.value;
        setCurrentState({
            ...currentState
        })
    }

    return (
        <div className="App">

            <Input value={currentState.ip} onChange={handleInput} type="text"/>

            <Button onClick={start}>
                Start
            </Button>

            {extracted()}
        </div>
    );
}

export default Ping