<?php

namespace App\Models;

use Illuminate\Database\Eloquent\Factories\HasFactory;
use Illuminate\Database\Eloquent\Model;

class Application extends Model
{
    use HasFactory;
    
    protected $fillable = [
        'degree', 'session', 'rollNumber', 'batch', 'department',
        'registrationNum', 'reason', 'rev_from', 'rev_to', 'candidateName',
        'cnic', 'fatherName', 'cgpa', 'dob', 'institute', 'address',
        'contact', 'status', 'coRemarks', 'hodRemarkds'
    ];
}
