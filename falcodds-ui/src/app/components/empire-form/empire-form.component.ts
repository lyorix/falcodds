import { Component, ChangeDetectionStrategy, Output, EventEmitter } from '@angular/core';

@Component({
  selector: 'app-empire-form',
  templateUrl: './empire-form.component.html',
  styleUrls: ['./empire-form.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class EmpireFormComponent {

  empirePlan: string;
  @Output() submitPlan = new EventEmitter<string>();

  constructor() {
  }

  public updateEmpirePlan(empirePlan: string): void {
    this.empirePlan = empirePlan;
  }

  public submit(): void {
    this.submitPlan.emit(this.empirePlan);
  }
}
