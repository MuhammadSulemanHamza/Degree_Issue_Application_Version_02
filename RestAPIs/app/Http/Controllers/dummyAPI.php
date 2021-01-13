<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;

class dummyAPI extends Controller
{
    //
    function getData(){
        return ["name"=>"Muhammad Suleman Hamza", 
        "email"=>"muhammadsuleman964@gmail.com"];
    }
}
