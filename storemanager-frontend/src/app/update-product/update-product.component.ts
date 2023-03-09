import { Component } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Product } from '../product';
import { ProductService } from '../product.service';

@Component({
  selector: 'app-update-product',
  templateUrl: './update-product.component.html',
  styleUrls: ['./update-product.component.css']
})
export class UpdateProductComponent {
  id: number|undefined;
  product: Product = new Product();
  constructor(private productService:ProductService, private router: Router, private route:ActivatedRoute) { }

  ngOnInit(): void{
    this.id=this.route.snapshot.params['id'];
    this.productService.getProductById(this.id).subscribe(data => {
      this.product = data;
    }), (error: any) => console.log(error);
  }


  goToProductList(){
    this.router.navigate(['/products']);
  }

  onSubmit()
  {
    this.productService.updateProduct(this.id,this.product).subscribe( data =>{
      this.goToProductList();
    }), (error: any) => console.log(error);
  }
}
