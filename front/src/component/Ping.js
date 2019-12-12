import React, {useEffect, useState} from "react";
import Axios from "axios";
import Input from "@material-ui/core/Input";
import Button from "@material-ui/core/Button";

function Ping() {

    const [currentState, setCurrentState] = useState({
        routes: [],
        ip: "",
        ttl: 0
    });


    useEffect(() => {
        function next() {
            const {ip, ttl} = currentState;

            if (ip === "") {
                return
            }

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
        next()
    }, [currentState.ttl]);


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

            <Input value={currentState.ip} onChange={handleInput}/>

            <Button onClick={start}>
                Start
            </Button>

            {extracted()}
        </div>
    );
}

export default Ping