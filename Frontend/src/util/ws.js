import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'

// Here is code responsible for websockets functionality
// like send message, handle websocket, connect and disconnect

let stompClient = null
const handlers = []

export function connect() {
    const socket = new SockJS('http://localhost:8080/ownsite') // connecting to the Spring server
    stompClient = Stomp.over(socket)
    stompClient.debug = () => {}
    stompClient.connect({}, frame => {
        stompClient.subscribe('/topic/method', message => {
            handlers.forEach(handler => handler(JSON.parse(message.body)))
            console.log(message)
        })
    })
    console.log('Connected')
}

export function addHandler(handler) {
    handlers.push(handler)
}

export function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect()
    }
    console.log("Disconnected")
}

export function sendMsg(message) {
    stompClient.send("/app/hello", {}, JSON.stringify(message)) // send message on specified address
    console.log("Sent")
}
