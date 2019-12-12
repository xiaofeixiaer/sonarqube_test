import React, {useCallback, useState} from "react";
import Axios from "axios";
import Input from "@material-ui/core/Input";
import Button from "@material-ui/core/Button";

function Ping() {

    const [currentState, setCurrentState] = useState({
        routes: [],
        ip: "",
        ttl: 1
    });

    useCallback(() => {
        next();
    }, currentState.ttl);

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
        })
    }

    function extracted(it) {
        return (
            <p>{JSON.parse(it)}</p>
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

            {currentState.routes.forEach((it) => {
                extracted(it);
            })}
        </div>
    );
}

export default Ping