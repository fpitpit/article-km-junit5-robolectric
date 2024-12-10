package fr.pitdev.article.kmtest

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform