import { TestBed } from '@angular/core/testing';
import { OddsService } from './odds.service';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

describe('OddsService', () => {
  let service: OddsService;
  let httpTestingController: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [
        HttpClientTestingModule
      ]
    });
    service = TestBed.inject(OddsService);
    httpTestingController = TestBed.inject(HttpTestingController);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  describe('given empire plan, when computeOdds', () => {
    const oddsMock = '81';
    it('should return odds value as string', () => {
      service.computeOdds('{"countdown": 8}').subscribe(res => {
        expect(res).toEqual('81');
      });
      const req = httpTestingController.expectOne('http://localhost:8080/odds');
      expect(req.request.method).toEqual('POST');
      req.flush(oddsMock);
      httpTestingController.verify();
    });
  });
});
