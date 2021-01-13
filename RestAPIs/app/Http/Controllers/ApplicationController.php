<?php

namespace App\Http\Controllers;

use Illuminate\Http\Request;
use App\Models\Application;

class ApplicationController extends Controller
{
    //
    function list($id=null)
    {
        return $id? Application::find($id):Application::all();
    }

    function add(Request $req)
    {
        $app = new Application;
        
        $app->degree = $req->degree;
        $app->session = $req->session;
        $app->rollNumber = $req->rollNumber;
        $app->batch = $req->batch;
        $app->department = $req->department;
        $app->registrationNum = $req->registrationNum;
        $app->reason = $req->reason;
        $app->rev_from = $req->rev_from;
        $app->rev_to = $req->rev_to;
        $app->candidateName = $req->candidateName;
        $app->cnic = $req->cnic;
        $app->fatherName = $req->fatherName;
        $app->cgpa = $req->cgpa;
        $app->dob = $req->dob;
        $app->institute = $req->institute;
        $app->address = $req->address;
        $app->contact = $req->contact;
        $app->status = $req->status;
        $app->coRemarks = $req->coRemarks;
        $app->hodRemarkds = $req->hodRemarkds;

        $res = $app->save();
        if ($res) {
            return ["Result" => "Data Has been Saved"];
        }
        else {
            return ["Result" => "Operation Failed"];
        }
    }

    function update(Request $req){
        $app = Application::find($req->id);
        
        $app->degree = $req->degree;
        $app->session = $req->session;
        $app->rollNumber = $req->rollNumber;
        $app->batch = $req->batch;
        $app->department = $req->department;
        $app->registrationNum = $req->registrationNum;
        $app->reason = $req->reason;
        $app->rev_from = $req->rev_from;
        $app->rev_to = $req->rev_to;
        $app->candidateName = $req->candidateName;
        $app->cnic = $req->cnic;
        $app->fatherName = $req->fatherName;
        $app->cgpa = $req->cgpa;
        $app->dob = $req->dob;
        $app->institute = $req->institute;
        $app->address = $req->address;
        $app->contact = $req->contact;
        $app->status = $req->status;
        $app->coRemarks = $req->coRemarks;
        $app->hodRemarkds = $req->hodRemarkds;

        $res = $app->save();
        if ($res) {
            return ["Result" => "Data Has been Updated"];
        }
        else {
            return ["Result" => "Operation Failed"];
        }
        
    }

    function search($name){
        return Application::where("name","like","%".$name."%")->get();
    }

    function delete($id){

        $app = Application::find($id);

        if ($app) {
            $res = $app->delete();
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
