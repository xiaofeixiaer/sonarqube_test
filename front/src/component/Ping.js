import React, {useCallback, useEffect, useState} from "react";
import Axios from "axios";
import Input from "@material-ui/core/Input";
import Button from "@material-ui/core/Button";

function Ping() {

    const [currentState, setCurrentState] = useState({
        routes: [],
        ip: "",
        ttl: 1
    });

    useEffect(() => {
        next();
    }, [currentState.ttl]);

    function next() {
        const {ip, ttl} = currentState;

        Axios.post("/ping", {
            host: ip,
            ttl: ttl
        }).then((response) => {
            const {routes, ip, ttl} = currentState;
            routes.push(response.data);

            setCurrentState({
                routes: routes,
                ip: ip,
                ttl: ttl + 1
            });
        }).catch((e) => {
            console.log(e)
        })
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

            <Input value={currentState.ip} onChange={handleInput}/>

            <Button onClick={next}>
                Start
            </Button>

            {extracted()}
        </div>
    );
}

export default Ping