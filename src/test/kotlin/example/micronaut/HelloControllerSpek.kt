package example.micronaut

import io.micronaut.http.HttpStatus
import io.micronaut.http.client.RxHttpClient
import org.junit.jupiter.api.Assertions.assertEquals
import io.micronaut.context.ApplicationContext
import io.micronaut.runtime.server.EmbeddedServer
import org.junit.jupiter.api.Assertions.assertTrue
import org.spekframework.spek2.style.specification.describe
import org.spekframework.spek2.Spek

object HelloControllerSpec : Spek({

    describe("an application context") {
        var embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)

        context("embedded server retrieval") {

            it("should be running") {
                assertTrue(embeddedServer.isRunning())
            }
        }
    }

    describe("/helloController") {
        var embeddedServer : EmbeddedServer = ApplicationContext.run(EmbeddedServer::class.java)

        context("client retrieval") {
            val client: RxHttpClient = embeddedServer.applicationContext.createBean(RxHttpClient::class.java, embeddedServer.url)

            it("responds on endpoints /helloController") {
                assertEquals(HttpStatus.OK, client.toBlocking().exchange("/hello", String::class.java).status())
            }
        }
    }
})
