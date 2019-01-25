using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;

namespace Coordination.Server.Controllers
{
    public class CompletedJobsController : BaseController
    {
        // GET: CompletedJobs
        public ActionResult Index()
        {
            return View(db.Jobs.Where(job => job.Completed == true).ToList());
            
        }
    }
}