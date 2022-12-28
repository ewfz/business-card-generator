import { Component, OnInit } from '@angular/core';
import { FileUploadService } from '../services/file-upload.service';
import { User } from '../user';

@Component({
	selector: 'app-file-upload',
	templateUrl: './file-upload.component.html',
	styleUrls: ['./file-upload.component.css']
})
export class FileUploadComponent implements OnInit {

  types = ['A','B'];
  userModel = new User('','','','','','','','','');
  file!: File ;
	constructor(private fileUploadService: FileUploadService) { }

	ngOnInit(): void {
	}

	onChange(event) {
		this.file = event.target.files[0];
	}

	onUpload() {
		console.log(this.file);
    console.log(this.userModel.nomArabe);
		this.fileUploadService.upload(this.file,this.userModel).subscribe(
      res => {
        console.log(res);
        var file = window.URL.createObjectURL(res);
        window.open(file);
    }
		);
	}
}
