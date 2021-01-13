<?php

use Illuminate\Database\Migrations\Migration;
use Illuminate\Database\Schema\Blueprint;
use Illuminate\Support\Facades\Schema;

class CreateApplicationsTable extends Migration
{
    /**
     * Run the migrations.
     *
     * @return void
     */
    public function up()
    {
        Schema::create('applications', function (Blueprint $table) {
            $table->id();
            $table->string('degree');
            $table->string('session');
            $table->string('rollNumber');
            $table->string('batch');
            $table->string('department');
            $table->string('registrationNum');
            $table->string('reason');
            $table->string('rev_from');
            $table->string('rev_to');
            $table->string('candidateName');
            $table->string('cnic');
            $table->string('fatherName');
            $table->string('cgpa');
            $table->string('dob');
            $table->string('institute');
            $table->string('address');
            $table->string('contact');
            $table->string('status');
            $table->string('coRemarks');
            $table->string('hodRemarkds');
            $table->timestamps();
        });
    }

    /**
     * Reverse the migrations.
     *
     * @return void
     */
    public function down()
    {
        Schema::dropIfExists('applications');
    }
}
