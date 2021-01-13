<?php

use Illuminate\Http\Request;
use Illuminate\Support\Facades\Route;
use App\Http\Controllers\ApplicationController;
use App\Http\Controllers\UserController;
use App\Http\Controllers\MyUserController;
/*
|--------------------------------------------------------------------------
| API Routes
|--------------------------------------------------------------------------
|
| Here is where you can register API routes for your application. These
| routes are loaded by the RouteServiceProvider within a group which
| is assigned the "api" middleware group. Enjoy building your API!
|
*/

Route::middleware('auth:api')->get('/user', function (Request $request) {
    return $request->user();
});

Route::group(['middleware' => 'auth:sanctum'], function(){
    //All secure URL's
});

Route::get("list/{id?}", [ApplicationController::class, 'list']);

Route::put("update", [ApplicationController::class, 'update']);

Route::get("search/{name}", [ApplicationController::class, 'search']);

Route::delete("delete/{id}", [ApplicationController::class, 'delete']);

Route::post("add", [ApplicationController::class, 'add']);

Route::get("list_user/{id?}", [MyUserController::class, 'list']);

Route::put("update_user", [MyUserController::class, 'update']);

Route::get("search_user/{name}", [MyUserController::class, 'search']);

Route::delete("delete_user/{id}", [MyUserController::class, 'delete']);

Route::post("add_user", [MyUserController::class, 'add']);

Route::post("login",[UserController::class,'index']);


