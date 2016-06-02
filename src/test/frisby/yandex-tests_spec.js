/**
 * Created by hsenid on 6/2/16.
 */
var frisby = require('frisby');

frisby.create('Yandex language detection test')
    .get('https://translate.yandex.net/api/v1.5/tr.json/detect?key=trnsl.1.1.20160310T063945Z.14945888ac849b23.fc507cddeb7ec9d96e1255e0a348b1b4a076f9c3&text=hello')
    .expectStatus(200)
    .inspectJSON()
    .expectJSONTypes({
        code: Number,
        lang: String
    })
    .expectJSON({
        code: 200,
        lang: "en"
    })
    .toss();

frisby.create("Yandex text translation text")
    .get("https://translate.yandex.net/api/v1.5/tr.json/translate?key=trnsl.1.1.20160310T063945Z.14945888ac849b23.fc507cddeb7ec9d96e1255e0a348b1b4a076f9c3&lang=en-fr&text=child")
    .expectStatus(200)
    .inspectJSON()

    .expectJSONTypes({
        code: Number,
        lang: String,
        text:[]
    })
    .expectJSON({
        code: 200,
        lang: "en-fr",
        text:["enfant"]
    })
    .toss()

function test(){

}

frisby.create("Yandex get all languages text")
    .get("https://translate.yandex.net/api/v1.5/tr.json/getLangs?key=trnsl.1.1.20160310T063945Z.14945888ac849b23.fc507cddeb7ec9d96e1255e0a348b1b4a076f9c3")
    .expectStatus(200)
    .inspectJSON()

    .expectJSONTypes({
        dirs:[]
    })
    .expectJSON({
        dirs:["az-ru","be-bg","be-cs","be-de","be-en","be-es","be-fr","be-it","be-pl","be-ro","be-ru","be-sr","be-tr","bg-be","bg-ru","bg-uk","ca-en","ca-ru","cs-be","cs-en","cs-ru","cs-uk","da-en","da-ru","de-be","de-en","de-es","de-fr","de-it","de-ru","de-tr","de-uk","el-en","el-ru","en-be","en-ca","en-cs","en-da","en-de","en-el","en-es","en-et","en-fi","en-fr","en-hu","en-it","en-lt","en-lv","en-mk","en-nl","en-no","en-pt","en-ru","en-sk","en-sl","en-sq","en-sv","en-tr","en-uk","es-be","es-de","es-en","es-ru","es-uk","et-en","et-ru","fi-en","fi-ru","fr-be","fr-de","fr-en","fr-ru","fr-uk","hr-ru","hu-en","hu-ru","hy-ru","it-be","it-de","it-en","it-ru","it-uk","lt-en","lt-ru","lv-en","lv-ru","mk-en","mk-ru","nl-en","nl-ru","no-en","no-ru","pl-be","pl-ru","pl-uk","pt-en","pt-ru","ro-be","ro-ru","ro-uk","ru-az","ru-be","ru-bg","ru-ca","ru-cs","ru-da","ru-de","ru-el","ru-en","ru-es","ru-et","ru-fi","ru-fr","ru-hr","ru-hu","ru-hy","ru-it","ru-lt","ru-lv","ru-mk","ru-nl","ru-no","ru-pl","ru-pt","ru-ro","ru-sk","ru-sl","ru-sq","ru-sr","ru-sv","ru-tr","ru-uk","sk-en","sk-ru","sl-en","sl-ru","sq-en","sq-ru","sr-be","sr-ru","sr-uk","sv-en","sv-ru","tr-be","tr-de","tr-en","tr-ru","tr-uk","uk-bg","uk-cs","uk-de","uk-en","uk-es","uk-fr","uk-it","uk-pl","uk-ro","uk-ru","uk-sr","uk-tr"]
    })
    .toss()