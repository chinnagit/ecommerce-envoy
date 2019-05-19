const express = require('express')
const auth = require('basic-auth')
const app = express()
const port = 9080



const jwt = require('njwt')

app.get('/token', (req, res) => {
        const claims = {iss: 'fun-with-jwts', sub: 'AzureDiamond'}
        const token = jwt.create(claims, 'top-secret-phrase')
        token.setExpiration(new Date().getTime() + 5* 60 * 1000)
        res.send(token.compact())
    }
)

app.get('/validatetoken', (req, res) => {
    console.log("headers: "+req.headers)
    console.log("req.params: "+req.params)
    console.log("req.header.authorization: "+req.header('Authorization'))
    console.log("req.query.token: "+req.query.token)
    // const  token  = req.query.token
    const  token  = req.header('Authorization')
    console.log("token: "+token)
    jwt.verify(token, 'top-secret-phrase', (err, verifiedJwt) => {
        if(err){
            res.send(err.message)
        }else{
            res.status(200)
            res.end()
            // res.send(verifiedJwt)
        }
    })
    }
)


app.get('/certs', (req, res) => res.send({
    "keys" : [ { "e"   : "AQAB",
        "n"   : "kWp2zRA23Z3vTL4uoe8kTFptxBVFunIoP4t_8TDYJrOb7D1iZNDXVeEsYKp6ppmrTZDAgd-cNOTKLd4M39WJc5FN0maTAVKJc7NxklDeKc4dMe1BGvTZNG4MpWBo-taKULlYUu0ltYJuLzOjIrTHfarucrGoRWqM0sl3z2-fv9k",
        "kty" : "RSA",
        "kid" : "1" } ]
}))

app.use((req, res, next) => {
    // console.log(req.headers)
    // console.log(auth(req))
    //
    // const { name, pass } = auth(req)
    //
    // if(name === 'bala' && pass === 'chinna') {
    //     res.status(200)
    //     res.end()
    // } else {
    //     res.status(401)
    //     res.end('Unauthorized')
    // }

    console.log("req: "+req)
    const  token  = req.header('Authorization')
    console.log("token: "+token)
    jwt.verify(token, 'top-secret-phrase', (err, verifiedJwt) => {
        if(err){
            // res.send(err.message)
            res.status(401)
            res.end('Unauthorized')
        }else{
            res.status(200)
            res.end("valid")
            // res.send(verifiedJwt)
        }
    })
})

// app.use((req, res, next) => {
//     console.log(req.headers)
//     console.log(auth(req))
//
//     const { name, pass } = auth(req)
//
//     if(name === 'bala' && pass === 'chinna') {
//         res.status(200)
//         res.end()
//     } else {
//         res.status(401)
//         res.end('Unauthorized')
//     }
// })

app.listen(port, () => console.log(`Example app listening on port ${port}!`))