<?php

namespace Database\Seeders;

use Illuminate\Database\Seeder;
use Illuminate\Support\Facades\DB;
use Illuminate\Support\Facades\Hash;
class MyUserTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        DB::table('my_users')->insert([
            'user_name'=>'Muhammad', 
            'user_email'=>'a@b.com', 
            'user_password'=>Hash::make('password'), 
            'user_role'=>'User'
        ]);
        
    }
}
