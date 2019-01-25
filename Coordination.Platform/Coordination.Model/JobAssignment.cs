using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Coordination.Model
{
    public class JobAssignment : DatabaseObject
    {
        public virtual Job Job
        {
            get; set;
        }
        public virtual User User
        {
            get; set;
        }
        public bool Approved
        { get; set;}
    }
}
