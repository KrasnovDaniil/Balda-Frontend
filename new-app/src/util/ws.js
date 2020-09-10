import SockJS from 'sockjs-client'
import { Stomp } from '@stomp/stompjs'


let stompClient = null
const handlers = []

export function connect() {
    const socket = new SockJS('http://localhost:8080/ownsite') // it works
    stompClient = Stomp.over(socket)
    stompClient.debug = () => {}
    stompClient.connect({}, frame => {
        stompClient.subscribe('/topic/method', message => {
            handlers.forEach(handler => handler(JSON.parse(message.body)))
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
    stompClient.send("/app/hello", {}, JSON.stringify(message))
    console.log("Sent")
}