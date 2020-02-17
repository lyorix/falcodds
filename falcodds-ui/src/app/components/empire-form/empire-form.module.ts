import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EmpireFormComponent } from './empire-form.component';
import { MatButtonModule } from '@angular/material/button';
import { FileInputModule } from '../file-input/file-input.module';

@NgModule({
  declarations: [
    EmpireFormComponent
  ],
  imports: [
    CommonModule,
    MatButtonModule,
    FileInputModule
  ],
  exports: [
    EmpireFormComponent
  ]
})
export class EmpireFormModule { }
