package com.infinixsoft.coroutines

class PokemonRequest {
    var count: Int? = null
    var next: String? = null
    var previous: String? = null
    var results: List<Result>? = null
}

class Result {
    var name: String? = null
    var url: String? = null
}