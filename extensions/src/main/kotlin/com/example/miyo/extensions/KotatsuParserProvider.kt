package com.example.miyo.extensions

import org.koitharu.kotatsu.parsers.MangaLoaderContext
import org.koitharu.kotatsu.parsers.MangaParser
import org.koitharu.kotatsu.parsers.model.MangaParserSource

/**
 * Provider that returns Kotatsu parser instances using a provided `MangaLoaderContext`.
 * The app should construct and provide a `MangaLoaderContext` implementation (see Kotatsu Reference).
 */
class KotatsuParserProvider(private val loaderContext: MangaLoaderContext) {
    fun newParser(source: MangaParserSource): MangaParser = loaderContext.newParserInstance(source)
}
