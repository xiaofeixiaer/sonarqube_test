import React, {useEffect, useState} from "react";
import Axios from "axios";
import Input from "@material-ui/core/Input";
import Button from "@material-ui/core/Button";

function Ping() {

    const [currentState, setCurrentState] = useState({
        routes: [],
        host: "",
        ttl: 1
    });

    function isPingEnd(response, routes) {
        if (routes.length === 0) {
            return false;
        }
        return routes[routes.length - 1].from === response.data.from
    }

    function getLocation(ip) {
        return Axios.get("/location/native",
            {
                params: {host: ip}
            }
        )
    }

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

            if (isPingEnd(response, routes)) {
                return;
            }

            const data = response.data;
            const location = getLocation(data.from);

            location.then(locationResponse => {
                routes.push({...data, location: locationResponse.data});
                setCurrentState({
                    routes: routes,
                    host: host,
                    ttl: ttl + 1
                });
            }).catch(ex => {
                routes.push({...data});
                setCurrentState({
                    routes: routes,
                    host: host,
                    ttl: ttl + 1
                });
            });

        }).catch((e) => {
            console.log(e)
        })
    }

    useEffect(next, [currentState.ttl]);

    function extracted() {
        return (
            <p>{JSON.stringify(currentState.routes)}</p>
        )
    }

    function handleInput(event) {
        currentState.host = event.target.value;
        setCurrentState({
            ...currentState
        })
    }

    return (
        <div className="App">

            <Input value={currentState.ip} onChange={handleInput} type="text"/>

            <Button onClick={next}>
                Start
            </Button>

            {extracted()}
        </div>
    );
}

export default Ping