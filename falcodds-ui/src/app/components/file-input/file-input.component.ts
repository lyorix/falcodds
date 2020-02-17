import { Component, ChangeDetectorRef, EventEmitter, ChangeDetectionStrategy, Output } from '@angular/core';

@Component({
  selector: 'app-file-input',
  templateUrl: './file-input.component.html',
  styleUrls: ['./file-input.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class FileInputComponent {

  filename: string;
  @Output() openFile = new EventEmitter<string>();

  constructor(private cdr: ChangeDetectorRef) { }

  public open(event): void {
    if (event.target.files && event.target.files.length) {
      const reader = new FileReader();
      const [file] = event.target.files;
      this.filename = file.name;
      reader.readAsText(file);
      reader.onload = () => {
        this.openFile.emit(reader.result as string);
        this.cdr.markForCheck();
      };
    }
  }
}
