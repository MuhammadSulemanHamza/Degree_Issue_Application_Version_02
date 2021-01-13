<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
class ApplicationTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('applications')->insert([
            'degree'=>'BSCS',
            'session'=>'2017-2021',
            'rollNumber'=>'17271519-032',
            'batch'=>'2017',
            'department'=>'CS',
            'registrationNum'=>'123141213',
            'reason'=>'BSSSSSSSSSSSSS',
            'rev_from'=>'N/A',
            'rev_to'=>'N/A',
            'candidateName'=>'Muhammad',
            'cnic'=>'34201', 
            'fatherName'=>'Muhammad', 
            'cgpa'=>'Allhamdullilah! 3.5 ', 
            'dob'=>'16-08-1998', 
            'institute'=>'UOG', 
            'address'=>'@gmail.com',
            'contact'=>'03044335338', 
            'status'=>'Accepted', 
            'coRemarks'=>'Good', 
            'hodRemarkds'=>'Well'
        ]);
    }
}
