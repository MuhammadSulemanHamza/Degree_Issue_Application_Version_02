<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\MyUser;

class MyUserController extends Controller
{
    function list($id=null)
    {
        return $id? MyUser::find($id):MyUser::all();
    }

    function add(Request $req)
    {
        $myUser = new MyUser;
        $myUser->user_name = $req->user_name;
        $myUser->user_email = $req->user_email;
        $myUser->user_password = $req->user_password;
        $myUser->user_role = $req->user_role;
        $res = $myUser->save();
        if ($res) {
            return ["Result" => "Data Has been Saved"];
        }
        else {
            return ["Result" => "Operation Failed"];
        }
    }

    function update(Request $req){
        $myUser = MyUser::find($req->id);
        $myUser->user_name = $req->user_name;
        $myUser->user_email = $req->user_email;
        $myUser->user_password = $req->user_password;
        $myUser->user_role = $req->user_role;
        $res = $myUser->save();
        if ($res) {
            return ["Result" => "Data Has been Updated"];
        }
        else {
            return ["Result" => "Operation Failed"];
        }
        
    }

    function search($name){
        return MyUser::where("user_name","like","%".$name."%")->get();
    }

    function delete($id){

        $myUser = MyUser::find($id);

        if ($myUser) {
            $res = $myUser->delete();
            if ($res) {
                return ["Result" => "Record Has been Deleted"];
            }
            else {
                return ["Result" => "Operation Failed"];
            }
        }
        else {
            return ["Result" => "No Record Found"];
        }

    }
}
