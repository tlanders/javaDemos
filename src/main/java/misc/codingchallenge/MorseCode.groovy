package misc.codingchallenge

import groovy.json.JsonSlurper

class MorseCode {
    protected static final CHALLENGE_API_URL = 'https://thecodingchallenge.com/.netlify/functions/challengeContent'
    protected static final CHECK_ANSWER_API_URL = 'https://thecodingchallenge.com/.netlify/functions/challengeContentCheckAnswer?answer='

    static void main(String [] args) {
        String challengeData = new URI(CHALLENGE_API_URL).toURL().text

        Map challengeMap = new JsonSlurper().parseText(challengeData)

//        println "Challenge data: ${challengeMap}"
//        println "Challenge data: ${challengeMap['cipherKey']}"

        Map matchMap = challengeMap['cipherKey'].collectEntries {[it.found, it.substitute]}
        matchMap[' '] = ''
        matchMap['/'] = ' '
//        println matchMap

        String message = decodeMorse(challengeMap['cipherText'], matchMap)
        println message
    }

    protected static String decodeMorse(String cipherText, Map matchMap) {
        def cipherTextTokens = cipherText.split('((?<=[ /])|(?=[ /]))')
//        println cipherTextTokens
        def decodedMessage = cipherTextTokens.collect {token ->
            token != ' ' ? matchMap[token] : ''
        }
        return decodedMessage.findAll {it != ''}.join('')
    }
}