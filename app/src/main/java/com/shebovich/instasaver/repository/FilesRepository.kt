package com.shebovich.instasaver.repository

import android.os.Handler
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import java.io.IOException
import java.lang.StringBuilder
import javax.inject.Inject
import android.widget.Toast
import io.reactivex.rxjava3.core.Observable
import org.jsoup.nodes.Element


class FilesRepository @Inject constructor() {
    fun downloadImage(url: String): Observable<String> {
        val createObservable: Observable<String> = Observable.create { emitter ->
            var js: String?
            var ls1: ArrayList<String> = ArrayList()
            var ls2: ArrayList<String> = ArrayList()
            var images: ArrayList<String> = ArrayList()
            var videos: ArrayList<String> = ArrayList()

            var name = ""

            val doc2 = Jsoup.connect(url).get()
            val e1 = doc2.body().select("script[type=\"text/javascript\"]")
            val e: ArrayList<Element> = ArrayList<Element>()
            e.addAll(e1)
            js = e[0].data()
            var count = 0
            val b = "display_resources"
            val a = js!!.split(b.toRegex()).toTypedArray()
            if (a.size > 2) {
                var pic = false
                var jj = ""
                for (s in a) {
                    if (!s.contains("location")) {
                        ls1.add(s)
                    }
                }
                for (i in 0 until ls1.size) {
                    if (ls1[i].contains("video_url")) {
                        pic = false
                        val B = ","
                        val A: List<String> = ls1[i].split(B)
                        for (S in A) {
                            if (S.contains("video_url")) {
                                val C = "\"video_url\":\""
                                val D = S.split(C.toRegex()).toTypedArray()
                                for (E in D) {
                                    val F = "\""
                                    val G = E.split(F.toRegex()).toTypedArray()
                                    for (H in G) {
                                        if (H.length > 2) {
                                            videos.add(H)
                                            name += "video : $H\n\n"
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        pic = true
                        jj += ls1[i]
                    }
                }
                if (pic) {
                    val bb = ","
                    val aa = jj.split(bb.toRegex()).toTypedArray()
                    for (s in aa) {
                        if (s.contains("src")) {
                            ls2.add(s)
                        }
                    }
                    for (ii in 0 until ls2.size) {
                        val helper = ii + 1
                        if (helper % 3 == 0) {
                            val d = "\"src\":\""
                            val c: List<String> = ls2[ii].split(d)
                            for (s in c) {
                                val f = "\""
                                val g = s.split(f.toRegex()).toTypedArray()
                                for (h in g) {
                                    if (h.length > 2) {
                                        images.add(h)
                                        name += "Image : $h\n\n"
                                    }
                                }
                            }
                        }
                    }
                }
            } else {
                val doc = Jsoup.connect(url).get()
                val metaElemsimg = doc.select("meta[property=og:image]")
                val metaElemsvid = doc.select("meta[property=og:video:secure_url]")
                val image = metaElemsimg.attr("content")
                val video = metaElemsvid.attr("content")
                name += if (video == "") {
                    images.add(image)
                    "image : $image"
                } else {
                    videos.add(video)
                    "video : $video"
                }
            }
            emitter.onNext(name.replace("\\u0026","&"))
        }


        return createObservable
    }


}
