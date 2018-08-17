package app.hea.tgto.services

import org.commonmark.parser.Parser
import org.commonmark.renderer.html.HtmlRenderer

/**
 * Service that parses markdown string, and return html string.
 *
 * @author Ruslan Ibragimov
 */
interface MarkdownService {
    fun render(md: String): String
}

class CommonMarkMarkdownService : MarkdownService {
    private val parser = Parser.builder().build()
    private val renderer = HtmlRenderer.builder().build()

    override fun render(md: String): String {
        val document = parser.parse(md)
        return renderer.render(document)
    }
}
