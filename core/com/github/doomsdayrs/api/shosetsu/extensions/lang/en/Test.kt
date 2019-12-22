package com.github.doomsdayrs.api.shosetsu.extensions.lang.en

import com.github.doomsdayrs.api.shosetsu.extensions.lang.en.bestlightnovel.BestLightNovel
import com.github.doomsdayrs.api.shosetsu.extensions.lang.en.box_novel.BoxNovel
import com.github.doomsdayrs.api.shosetsu.extensions.lang.en.novel_full.NovelFull
import com.github.doomsdayrs.api.shosetsu.services.core.dep.Formatter
import com.github.doomsdayrs.api.shosetsu.services.core.objects.Novel
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.ResponseBody
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.IOException
import java.net.URL

/*
 * This file is part of shosetsu-extensions.
 * shosetsu-extensions is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * shosetsu-extensions is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with shosetsu-extensions.  If not, see https://www.gnu.org/licenses/.
 * ====================================================================
 */
/**
 * novelreader-extensions
 * 03 / June / 2019
 *
 * @author github.com/doomsdayrs
 */
internal class Test {

    companion object {
        // The below is methods robbed from ScrapeFormat class
        private val builder: Request.Builder = Request.Builder()
        private val client: OkHttpClient = OkHttpClient()
        @Throws(IOException::class)
        private fun request(url: String?): ResponseBody? {
            println(url)
            val u = URL(url)
            val request = builder.url(u).build()
            return client.newCall(request).execute().body()
        }

        @Throws(IOException::class)
        private fun docFromURL(URL: String): Document {
            return Jsoup.parse(request(URL)!!.string())
        }

        @Throws(IOException::class, InterruptedException::class)
        @JvmStatic
        fun main(args: Array<String>) {
            val formatter: Formatter = BoxNovel()
            val novelPages: List<Novel> = formatter.parseLatest(docFromURL(formatter.getLatestURL(1)))
            for (novel: Novel in novelPages)
                println(formatter.parseNovel(docFromURL(novel.link)))
        }
    }
}